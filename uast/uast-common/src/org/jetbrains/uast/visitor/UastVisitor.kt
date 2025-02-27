// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.uast.visitor

import org.jetbrains.uast.*

/**
 * A visitor for UAST elements.
 *
 * When an instance is passed to any [UElement]'s [UElement.accept] function, the approrpiate
 * `visit*` function will be called, depending on the actual type of the element.
 *
 * The default implementation for each `visit*` function other than [visitElement] is to delegate to
 * the `visit*` function for the element's supertype. That lets you implement only the most general
 * `visit*` method that applies to your use case. For example, if you want to visit all variables,
 * you can implement [visitVariable] instead of [visitParameter], [visitField], and
 * [visitLocalVariable].
 *
 * To visit the element's children as well, return `false` from the `visit*` function.
 *
 * If the `visit*` function returns `false`, then the visitor will be passed to the `accept`
 * function of each of the direct children of the element, and then the visitor's `afterVisit*` will
 * be called for the element's type. The default implementation for each `afterVisit*` function
 * other than [afterVisitElement] is to delegate to the `afterVisit*` function for the element's
 * supertype.
 */
interface UastVisitor {
  fun visitElement(node: UElement): Boolean

  fun visitFile(node: UFile): Boolean = visitElement(node)
  fun visitImportStatement(node: UImportStatement): Boolean = visitElement(node)
  fun visitDeclaration(node: UDeclaration): Boolean = visitElement(node)
  fun visitClass(node: UClass): Boolean = visitDeclaration(node)
  fun visitInitializer(node: UClassInitializer): Boolean = visitDeclaration(node)
  fun visitMethod(node: UMethod): Boolean = visitDeclaration(node)
  fun visitVariable(node: UVariable): Boolean = visitDeclaration(node)
  fun visitParameter(node: UParameter): Boolean = visitVariable(node)
  fun visitField(node: UField): Boolean = visitVariable(node)
  fun visitLocalVariable(node: ULocalVariable): Boolean = visitVariable(node)
  fun visitEnumConstant(node: UEnumConstant): Boolean = visitField(node)
  fun visitAnnotation(node: UAnnotation): Boolean = visitElement(node)

  // Expressions
  fun visitExpression(node: UExpression): Boolean = visitElement(node)
  fun visitLabeledExpression(node: ULabeledExpression): Boolean = visitExpression(node)
  fun visitDeclarationsExpression(node: UDeclarationsExpression): Boolean = visitExpression(node)
  fun visitBlockExpression(node: UBlockExpression): Boolean = visitExpression(node)
  fun visitQualifiedReferenceExpression(node: UQualifiedReferenceExpression): Boolean = visitExpression(node)
  fun visitSimpleNameReferenceExpression(node: USimpleNameReferenceExpression): Boolean = visitExpression(node)
  fun visitNamedExpression(node: UNamedExpression): Boolean = visitExpression(node)
  fun visitTypeReferenceExpression(node: UTypeReferenceExpression): Boolean = visitExpression(node)
  fun visitCallExpression(node: UCallExpression): Boolean = visitExpression(node)
  fun visitBinaryExpression(node: UBinaryExpression): Boolean = visitExpression(node)
  fun visitBinaryExpressionWithType(node: UBinaryExpressionWithType): Boolean = visitExpression(node)
  fun visitBinaryExpressionWithPattern(node: UBinaryExpressionWithPattern): Boolean = visitExpression(node)
  fun visitPatternExpression(node: UPatternExpression) = visitExpression(node)
  fun visitPolyadicExpression(node: UPolyadicExpression): Boolean = visitExpression(node)
  fun visitParenthesizedExpression(node: UParenthesizedExpression): Boolean = visitExpression(node)
  fun visitUnaryExpression(node: UUnaryExpression): Boolean = visitExpression(node)
  fun visitPrefixExpression(node: UPrefixExpression): Boolean = visitExpression(node)
  fun visitPostfixExpression(node: UPostfixExpression): Boolean = visitExpression(node)
  fun visitExpressionList(node: UExpressionList): Boolean = visitExpression(node)
  fun visitIfExpression(node: UIfExpression): Boolean = visitExpression(node)
  fun visitSwitchExpression(node: USwitchExpression): Boolean = visitExpression(node)
  fun visitSwitchClauseExpression(node: USwitchClauseExpression): Boolean = visitExpression(node)
  fun visitWhileExpression(node: UWhileExpression): Boolean = visitExpression(node)
  fun visitDoWhileExpression(node: UDoWhileExpression): Boolean = visitExpression(node)
  fun visitForExpression(node: UForExpression): Boolean = visitExpression(node)
  fun visitForEachExpression(node: UForEachExpression): Boolean = visitExpression(node)
  fun visitTryExpression(node: UTryExpression): Boolean = visitExpression(node)
  fun visitCatchClause(node: UCatchClause): Boolean = visitElement(node)
  fun visitLiteralExpression(node: ULiteralExpression): Boolean = visitExpression(node)
  fun visitThisExpression(node: UThisExpression): Boolean = visitExpression(node)
  fun visitSuperExpression(node: USuperExpression): Boolean = visitExpression(node)
  fun visitReturnExpression(node: UReturnExpression): Boolean = visitExpression(node)
  fun visitBreakExpression(node: UBreakExpression): Boolean = visitExpression(node)
  fun visitYieldExpression(node: UYieldExpression): Boolean = visitExpression(node)
  fun visitContinueExpression(node: UContinueExpression): Boolean = visitExpression(node)
  fun visitThrowExpression(node: UThrowExpression): Boolean = visitExpression(node)
  fun visitArrayAccessExpression(node: UArrayAccessExpression): Boolean = visitExpression(node)
  fun visitCallableReferenceExpression(node: UCallableReferenceExpression): Boolean = visitExpression(node)
  fun visitClassLiteralExpression(node: UClassLiteralExpression): Boolean = visitExpression(node)
  fun visitLambdaExpression(node: ULambdaExpression): Boolean = visitExpression(node)
  fun visitObjectLiteralExpression(node: UObjectLiteralExpression): Boolean = visitExpression(node)

  fun visitComment(node: UComment): Boolean = visitElement(node)

  // After

  fun afterVisitElement(node: UElement) { }

  fun afterVisitFile(node: UFile) = afterVisitElement(node)
  fun afterVisitImportStatement(node: UImportStatement) = afterVisitElement(node)
  fun afterVisitDeclaration(node: UDeclaration) = afterVisitElement(node)
  fun afterVisitClass(node: UClass) = afterVisitDeclaration(node)
  fun afterVisitInitializer(node: UClassInitializer) = afterVisitDeclaration(node)
  fun afterVisitMethod(node: UMethod) = afterVisitDeclaration(node)
  fun afterVisitVariable(node: UVariable) = afterVisitElement(node)
  fun afterVisitParameter(node: UParameter) = afterVisitVariable(node)
  fun afterVisitField(node: UField) = afterVisitVariable(node)
  fun afterVisitLocalVariable(node: ULocalVariable) = afterVisitVariable(node)
  fun afterVisitEnumConstant(node: UEnumConstant) = afterVisitField(node)
  fun afterVisitAnnotation(node: UAnnotation) = afterVisitElement(node)
  // Expressions
  fun afterVisitExpression(node: UExpression) = afterVisitElement(node)
  fun afterVisitLabeledExpression(node: ULabeledExpression) = afterVisitExpression(node)
  fun afterVisitDeclarationsExpression(node: UDeclarationsExpression) = afterVisitExpression(node)
  fun afterVisitBlockExpression(node: UBlockExpression) = afterVisitExpression(node)
  fun afterVisitQualifiedReferenceExpression(node: UQualifiedReferenceExpression) = afterVisitExpression(node)
  fun afterVisitSimpleNameReferenceExpression(node: USimpleNameReferenceExpression) = afterVisitExpression(node)
  fun afterVisitNamedExpression(node: UNamedExpression) = afterVisitExpression(node)
  fun afterVisitTypeReferenceExpression(node: UTypeReferenceExpression) = afterVisitExpression(node)
  fun afterVisitCallExpression(node: UCallExpression) = afterVisitExpression(node)
  fun afterVisitBinaryExpression(node: UBinaryExpression) = afterVisitExpression(node)
  fun afterVisitBinaryExpressionWithType(node: UBinaryExpressionWithType) = afterVisitExpression(node)
  fun afterVisitBinaryExpressionWithPattern(node: UBinaryExpressionWithPattern) = afterVisitExpression(node)
  fun afterVisitPatternExpression(node: UPatternExpression) = afterVisitExpression(node)
  fun afterVisitParenthesizedExpression(node: UParenthesizedExpression) = afterVisitExpression(node)
  fun afterVisitUnaryExpression(node: UUnaryExpression) = afterVisitExpression(node)
  fun afterVisitPrefixExpression(node: UPrefixExpression) = afterVisitExpression(node)
  fun afterVisitPostfixExpression(node: UPostfixExpression) = afterVisitExpression(node)
  fun afterVisitExpressionList(node: UExpressionList) = afterVisitExpression(node)
  fun afterVisitIfExpression(node: UIfExpression) = afterVisitExpression(node)
  fun afterVisitSwitchExpression(node: USwitchExpression) = afterVisitExpression(node)
  fun afterVisitSwitchClauseExpression(node: USwitchClauseExpression) = afterVisitExpression(node)
  fun afterVisitWhileExpression(node: UWhileExpression) = afterVisitExpression(node)
  fun afterVisitDoWhileExpression(node: UDoWhileExpression) = afterVisitExpression(node)
  fun afterVisitForExpression(node: UForExpression) = afterVisitExpression(node)
  fun afterVisitForEachExpression(node: UForEachExpression) = afterVisitExpression(node)
  fun afterVisitTryExpression(node: UTryExpression) = afterVisitExpression(node)
  fun afterVisitCatchClause(node: UCatchClause) = afterVisitElement(node)
  fun afterVisitLiteralExpression(node: ULiteralExpression) = afterVisitExpression(node)
  fun afterVisitThisExpression(node: UThisExpression) = afterVisitExpression(node)
  fun afterVisitSuperExpression(node: USuperExpression) = afterVisitExpression(node)
  fun afterVisitReturnExpression(node: UReturnExpression) = afterVisitExpression(node)
  fun afterVisitBreakExpression(node: UBreakExpression) = afterVisitExpression(node)
  fun afterVisitYieldExpression(node: UYieldExpression) = afterVisitExpression(node)
  fun afterVisitContinueExpression(node: UContinueExpression) = afterVisitExpression(node)
  fun afterVisitThrowExpression(node: UThrowExpression) = afterVisitExpression(node)
  fun afterVisitArrayAccessExpression(node: UArrayAccessExpression) = afterVisitExpression(node)
  fun afterVisitCallableReferenceExpression(node: UCallableReferenceExpression) = afterVisitExpression(node)
  fun afterVisitClassLiteralExpression(node: UClassLiteralExpression) = afterVisitExpression(node)
  fun afterVisitLambdaExpression(node: ULambdaExpression) = afterVisitExpression(node)
  fun afterVisitObjectLiteralExpression(node: UObjectLiteralExpression) = afterVisitExpression(node)
  fun afterVisitPolyadicExpression(node: UPolyadicExpression) = afterVisitExpression(node)

  fun afterVisitComment(node: UComment) = afterVisitElement(node)
}

/** A [UastVisitor] that visits each element's children by default. */
abstract class AbstractUastVisitor : UastVisitor {
  override fun visitElement(node: UElement): Boolean = false
}

/**
 * A [UastVisitor] that does not visit each element's children by default.
 *
 * If passing an instance to [com.intellij.uast.UastVisitorAdapter], make sure that no implemented
 * function returns `false`, since that class requires a never-traversing visitor.
 */
abstract class AbstractUastNonRecursiveVisitor : UastVisitor {
  override fun visitElement(node: UElement): Boolean = true
}

/** A [UastVisitor] that visits each element's children but does nothing at each element. */
@Suppress("unused")
object EmptyUastVisitor : AbstractUastVisitor()
