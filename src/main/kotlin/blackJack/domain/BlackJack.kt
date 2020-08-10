package blackJack.domain

class BlackJack(names: List<String>) {
    val players = makePlayer(names)

    private fun makePlayer(names: List<String>): List<Player> = names.map { Player(it) }

}
