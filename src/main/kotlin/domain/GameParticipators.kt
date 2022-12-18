package domain

class GameParticipators(players: List<GameParticipator>) {

    val participators: ArrayDeque<GameParticipator>

    private val finishParticipators: MutableList<GameParticipator> = mutableListOf()

    init {
        participators = ArrayDeque(players)
        participators.addLast(Dealer())
    }

    fun currentParticipator(): GameParticipator {
        val currentParticipator = participators.removeFirst()
        participators.addLast(currentParticipator)
        return currentParticipator
    }

    fun quitGame(participator: GameParticipator) {
        val isRemoved = participators.removeIf { it == participator }
        if (isRemoved.not()) {
            return
        }
        finishParticipators.add(participator)
    }

    fun finishParticipators(): List<GameParticipator> {
        finishParticipators.addAll(participators)
        return finishParticipators.toList()
    }

    fun findWinner(): Map<Player, WinStatus> {
        val dealer = finishParticipators.filterIsInstance<Dealer>().single()
        return finishParticipators.filterIsInstance<Player>().associateWith {
            val winner = dealer.pickWinner(it)
            WinStatus.valueOf(winner)
        }
    }

    fun isGameEnd(): Boolean {
        if (participators.isEmpty()) {
            return true
        }
        val dealer = participators.filterIsInstance<Dealer>().firstOrNull()
        if (dealer != null) {
            return dealer.isLoser()
        }
        return false
    }

    fun size() = participators.size
}
