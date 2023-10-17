package com.zoolcoder.employee.computer.registry.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ServiceControllerLayerRulesTest {

  private static final String BASE_PACKAGE = "com.zoolcoder.employee.computer.registry";

  @Test
  public void servicesShouldNotDependOnControllers() {
    JavaClasses classes = new ClassFileImporter().importPackages(BASE_PACKAGE);

    ArchRule rule = noClasses()
        .that().resideInAPackage("..service..")
        .should().dependOnClassesThat().resideInAPackage("..controller..");

    rule.check(classes);
  }

  @Test
  public void controllersShouldDependOnServices() {
    JavaClasses classes = new ClassFileImporter().importPackages(BASE_PACKAGE);

    ArchRule rule = classes()
        .that().resideInAPackage("..controller..")
        .should().dependOnClassesThat().resideInAPackage("..service..");

    rule.check(classes);
  }
}
