package blackjack.domain

data class GameResult(val dealerRecord: Money, val playerRecords: List<Pair<String, Money>>)
