package blackjack

interface Score

@JvmInline
value class SingleScore(val value: Int) : Score

@JvmInline
value class SelectableScore(val scores: List<SingleScore>) : Score
