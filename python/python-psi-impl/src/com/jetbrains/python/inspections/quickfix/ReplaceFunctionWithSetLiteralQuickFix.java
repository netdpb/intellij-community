/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jetbrains.python.inspections.quickfix;

import com.intellij.modcommand.ModPsiUpdater;
import com.intellij.modcommand.PsiUpdateModCommandQuickFix;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.PyPsiBundle;
import com.jetbrains.python.inspections.PySetFunctionToLiteralInspection;
import com.jetbrains.python.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * User : catherine
 * Quick Fix to replace function call of built-in function "set" with
 * set literal if applicable
 */
public class ReplaceFunctionWithSetLiteralQuickFix extends PsiUpdateModCommandQuickFix {
  @Override
  public @NotNull String getFamilyName() {
    return PyPsiBundle.message("QFIX.replace.function.set.with.literal");
  }

  @Override
  public void applyFix(@NotNull Project project, @NotNull PsiElement element, @NotNull ModPsiUpdater updater) {
    PyElement[] elements = PySetFunctionToLiteralInspection.getSetCallArguments((PyCallExpression)element);
    PyElementGenerator elementGenerator = PyElementGenerator.getInstance(project);
    StringBuilder str = new StringBuilder("{");
    for (int i = 0; i != elements.length; ++i) {
      PyElement e = elements[i];
      str.append(e.getText());
      if (i != elements.length-1)
        str.append(", ");
    }
    str.append("}");
    element.replace(elementGenerator.createFromText(LanguageLevel.forElement(element), PyExpressionStatement.class,
                                                             str.toString()).getExpression());
  }
}
