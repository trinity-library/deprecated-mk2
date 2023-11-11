package sisterhood.application.navi

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class StackTest : ExpectSpec() {
    init {
        expect("Stack behaves as expected") {
            // Given
            val stack = arrayDequeOf(0, 1, 2)

            // When
            stack.push(3)

            // Then
            stack.pop() shouldBe 3
            stack.peek() shouldBe 2
        }
    }
}
