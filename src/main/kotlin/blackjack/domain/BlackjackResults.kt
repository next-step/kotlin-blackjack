package blackjack.domain

data class BlackjackResults(val dealerResult: DealerResult, val gamblerResults: List<GamblerResult>)
