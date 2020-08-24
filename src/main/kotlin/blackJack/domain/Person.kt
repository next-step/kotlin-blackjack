package blackJack.domain

class Person(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 없을수 없습니다." }
    }

    val hands: List<Card> = listOf()
}
