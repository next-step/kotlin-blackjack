package blackjack

fun main() {
    val blackJack = BlackJack()
    val players = blackJack.parsePlayers(inputName())
}

class BlackJack {

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(
            names.split(",").map {
                require(it.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
                Player(it)
            }
        )
    }
}
