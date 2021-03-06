package cn.unicom.processor;

import static javax.lang.model.element.ElementKind.PACKAGE;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import cn.unicom.viewbind.BindLayout;
import cn.unicom.viewbind.BindView;

@AutoService(Process.class)
public class CBindLayoutProcessor extends AbstractProcessor {
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager.printMessage(Diagnostic.Kind.NOTE,"CBindLayoutProcessor init:");
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE,"process:");
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindLayout.class);
        for (Element element : elementsAnnotatedWith) {
            generateBinderClass((TypeElement)element);
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindLayout.class.getCanonicalName());
    }

    private void generateBinderClass(TypeElement element) {

        ClassName activityClassName = ClassName.get(element);

        ParameterSpec activityParam = ParameterSpec.builder(activityClassName, "activity")
                .addModifiers(Modifier.FINAL)
                .build();

        List<VariableElement> bindViewFieldList = getFieldElementsWithAnnotation(element, BindView.class);
        CodeBlock.Builder bindViewCodeBlockBuilder = CodeBlock.builder();
        for (VariableElement variableElement : bindViewFieldList) {
            String variableName = variableElement.getSimpleName().toString();
            TypeName viewType = ClassName.bestGuess(variableElement.asType().toString());
            int viewId = variableElement.getAnnotation(BindView.class).value();
            bindViewCodeBlockBuilder.addStatement("activity.$L=($T)activity.findViewById($L)",variableName,viewType,viewId);
        }

        MethodSpec bindViewMethod = MethodSpec.methodBuilder("bindView")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(activityParam)
                .addCode(bindViewCodeBlockBuilder.build())
                .returns(void.class)
                .build();

        MethodSpec constructorMethod = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(activityParam)
                .addStatement("$N($L)", bindViewMethod, activityParam.name)
                .build();

        String binderClassName = element.getSimpleName().toString();
        TypeSpec delegateType = TypeSpec.classBuilder(binderClassName + "DelegateBinder")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(bindViewMethod)
                .addMethod(constructorMethod)
                .build();
        JavaFile javaFile = JavaFile.builder(getPackage(element).getQualifiedName().toString(), delegateType)
                .addFileComment("This file is generated by Binder, do not edit!")
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
            System.out.println(getPackage(element).getQualifiedName().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<VariableElement> getFieldElementsWithAnnotation(TypeElement typeElement, Class clazz){
        List<VariableElement> elements=new ArrayList<>();
        for(Element element:typeElement.getEnclosedElements()){
            if(element.getAnnotation(clazz)!=null){
                //????????????????????????????????????????????????????????????????????????????????????
                elements.add((VariableElement) element);
            }
        }
        return elements;
    }

    public static PackageElement getPackage(Element element) {
        while (element.getKind() != PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }
}