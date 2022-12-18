package domain

class GameParticipators(players: List<GameParticipator>) {

    val participators: ArrayDeque<GameParticipator>

    private val finishParticipators :MutableList<GameParticipator> = mutableListOf()
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
        finishParticipators.addAll(participators);
        return finishParticipators.toList()
    }

    fun size() = participators.size

    fun isGameEnd(): Boolean {
        if (this.participators.isEmpty()) {
            return true
        }
        this.participators.find { it is Dealer } ?.let{
            return it.isLoser()
        }
        return false
    }
}
