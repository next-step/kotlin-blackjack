package blackjack.domain.participant

import blackjack.domain.participant.state.role.Role

@JvmInline
value class Participants(private val values: List<Role>) {
    init {
        require(values.size >= MINIMUM_NUMBER_OF_PLAYERS) { "플레이어는 2명 이상이어야 합니다." }
        require(values.map { it.name }.toSet().size == values.size) { "플레이어의 이름은 중복될 수 없습니다." }
    }

    constructor(vararg role: Role) : this(role.toList())

    fun getPlayers(): List<Role> {
        return values.filter { !it.isDealer() }.map { it as Player }
    }

    fun getDealer(): Dealer {
        return values.filter { it.isDealer() }.map { it as Dealer }.first()
    }

    fun getAll(): List<Role> {
        return values
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
    }
}
