package blackjack.domain

class Participants(
    private val value: List<Participant>,
) {
    private var drawOrder: Int = 0

    fun readAll(): List<Participant> = value.toList()

    fun findByName(participantName: ParticipantName) = value.find { it.name == participantName }

    fun findDrawPlayer(): Participant? {
        val startOrder = drawOrder
        val targetOrder = (startOrder..< value.size)
            .firstOrNull { value[it].canDraw() }

        if (targetOrder == null) {
            return null
        }

        this.drawOrder = (targetOrder + 1) % value.size
        return value[targetOrder]
    }

    fun isAllPlayerStopDraw() = value.all { !it.canDraw() }

    fun canDrawForAllPlayers() = !isAllPlayerStopDraw()

}
