package blackjack.domain

@JvmInline
value class Player(
    val name: String,
) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈칸 혹은 여백을 허용하지 않습니다. name = $name" }
    }
}
