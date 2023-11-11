package sisterhood.application.navi

fun <T> arrayDequeOf(vararg elements: T) = ArrayDeque(elements.toList())

fun <T> ArrayDeque<T>.peek() = lastOrNull()

fun <T> ArrayDeque<T>.push(element: T) = addLast(element) // returns Unit

fun <T> ArrayDeque<T>.pop() = removeLastOrNull()          // returns T?
