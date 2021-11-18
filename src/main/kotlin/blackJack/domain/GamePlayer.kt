package blackJack.domain

open class GamePlayer(val name: String, val status: PlayerStatusImpl = PlayerStatusImpl.of()) : PlayerStatus by status
