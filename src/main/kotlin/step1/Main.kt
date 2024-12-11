package step1

fun main() {
    StringUtils.lastChar("Kotlin")
    "Kotlin".lastChar()
    1.to("one")
    1 to2 "one" // Infix notation
    Point(0, 1).plus(Point(1, 2))
    Point2(0, 1) + Point2(1, 2) // operator overload

    val names = listOf("Jason", "Pobi")
    names.get(0)
    names[0] // index access operator

    check(false, { -> "Check failed." })
    check(false) { "Check failed." } // 람다 밖으로 뺴내는 관례

    val sb = StringBuilder()
    sb.append("Yes")
    sb.append("No")

    val sb2 = StringBuilder()
    sb2.apply {
        this.append("Yes")
        append("No")
    }
}
