package blackjack.domain

data class GameResult(val dealerRecord: Map<OutcomeType, Int>, val playerRecords: Map<Player, OutcomeType>)
