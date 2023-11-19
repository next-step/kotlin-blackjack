package blackjack

sealed class GameParticipantResult{
    data class Player(val name: String, val matchResult: MatchResult)
    data class Dealer(val name: String, val matchResults: List<MatchResult>) {
        fun countByMatchResult() : Pair<Int, Int> =
            matchResults.let {
                var win = 0
                var loss = 0
                for(result in matchResults) {
                    when(result) {
                        MatchResult.WIN -> win++
                        MatchResult.LOSS -> loss++
                        MatchResult.DRAW -> Unit
                    }
                }
                win to loss
            }
    }
}
