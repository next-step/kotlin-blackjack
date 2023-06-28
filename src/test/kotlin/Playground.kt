import io.kotest.core.spec.style.StringSpec

class Playground: StringSpec({
    "List를 shuffled 한다." {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val shuffled = numbers.shuffled()

        println(numbers)
        println(shuffled)
    }
})
