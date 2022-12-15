package domain

class GameParticipators(players: List<GameParticipator>) {

    val participators: ArrayDeque<GameParticipator>

    init {
        participators = ArrayDeque(players)
        participators.add(Dealer())
    }
    fun currentParticipator(): GameParticipator {
        val currentParticipator = participators.removeFirst()
        participators.addLast(currentParticipator)
        return currentParticipator
    }

    fun quitGame(participator: GameParticipator) {
        participators.removeIf { it == participator }
    }

    fun size() = participators.size

    fun isNotEmpty() = participators.isNotEmpty()
}
