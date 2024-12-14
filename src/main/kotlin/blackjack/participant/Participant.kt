package blackjack.participant

abstract class Participant(
    private val name: PlayerName
) {
    fun getName(): PlayerName {
        return name
    }
}
