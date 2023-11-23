package blackjack_dealer.entity.state

enum class ParticipantResultState(val state: String) {
    WIN("승"), LOSE("패"), DRAW("무");

    companion object {
        private const val DEFAULT_VALUE = 0

        fun getParticipantResultStateGroup(groupingByParticipantResult: Map<ParticipantResultState, Int>) =
            values().associateWith { groupingByParticipantResult.getOrDefault(it, DEFAULT_VALUE) }
    }
}
