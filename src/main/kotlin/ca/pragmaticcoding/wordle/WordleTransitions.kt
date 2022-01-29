package ca.pragmaticcoding.wordle

import javafx.animation.*
import javafx.scene.Node
import javafx.scene.transform.Rotate
import javafx.util.Duration

private const val speed = 400.0
private const val delay = 400.0

fun flipTile(node: Node, column: Int, letterStatus: LetterStatus) {
   SequentialTransition(PauseTransition(Duration(column * delay)), RotateTransition(Duration.millis(speed), node).apply {
      byAngle = 90.0
      axis = Rotate.X_AXIS
      setOnFinished { LetterStatus.updatePseudoClass(node, letterStatus) }
   }, RotateTransition(Duration.millis(speed), node).apply {
      byAngle = -90.0
      axis = Rotate.X_AXIS
   }).play()
}

fun flashTile(node: Node) {
   SequentialTransition(ScaleTransition(Duration.millis(20.0), node).apply {
      toX = 1.08
      toY = 1.08
      cycleCount = 2
      isAutoReverse = true
   }, ScaleTransition(Duration.millis(20.0), node).apply {
      toX = 0.92
      toY = 0.92
      cycleCount = 2
      isAutoReverse = true
   }).play()
}

fun wiggleRow(node: Node) {
   node.translateX = -5.0
   TranslateTransition(Duration.millis(50.0), node).apply {
      byX = 10.0
      cycleCount = 6
      isAutoReverse = true
      setOnFinished { node.translateX = 0.0 }
      play()
   }
}

fun showToast(node: Node) {
   SequentialTransition(FadeTransition(Duration(200.0), node).apply {
      fromValue = 0.0
      toValue = 1.0
   }, PauseTransition(Duration(1000.0)), FadeTransition(Duration(200.0), node).apply {
      fromValue = 1.0
      toValue = 0.0
   }).play()
}
