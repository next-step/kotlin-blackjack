package blackjack.domain.participant

import blackjack.application.Deck
import blackjack.domain.participant.state.Name
import blackjack.domain.participant.state.role.Role

@JvmInline
value class Participants(private val values: List<Role>) {
    init {
        require(values.size >= MINIMUM_NUMBER_OF_PLAYERS) { "참가자는 2명 이상이어야 합니다." }
        require(values.map { it.name }.toSet().size == values.size) { "참가자의 이름은 중복될 수 없습니다." }
    }

    constructor(vararg role: Role) : this(role.toList())

    operator fun plus(role: Role): Participants {
        if (role is Dealer) {
            val participants = values.toMutableList()
            participants.add(0, role)
            return Participants(participants)
        }
        return Participants(values + role)
    }

    fun getPlayers(): List<Player> {
        return values.filter { !it.isDealer() }.map { it as Player }
    }

    fun getDealer(): Dealer {
        return values.filter { it.isDealer() }.map { it as Dealer }.first()
    }

    fun getAll(): List<Role> {
        return values
    }

    fun isBlackjack(): Boolean {
        return values.any { it.isBlackjack() }
    }

    companion object {
        const val NUMBER_OF_INIT_CARDS = 2
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2

        fun createPlayers(names: Array<Name>, deck: Deck): Participants {
            return Participants(names.map { Player(it.toString(), deck.getCards(NUMBER_OF_INIT_CARDS)) })
        }
    }
}
