import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class Playground: StringSpec({
    "List를 shuffled하면 무작위로 섞인 새로운 리스트가 반환 된다." {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val shuffled = numbers.shuffled()

        println(numbers)
        println(shuffled)

        numbers shouldNotBe shuffled
    }
})
