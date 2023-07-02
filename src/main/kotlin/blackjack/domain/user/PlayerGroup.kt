package blackjack.domain.user

class PlayerGroup(val players: List<Player>) {

    // same JVM signature 에러
    constructor(playerNames: List<String>, @Suppress("UNUSED_PARAMETER") dummyImplicit: Any? = null) : this(playerNames.map { playerName -> Player(playerName) }.toList())
}
