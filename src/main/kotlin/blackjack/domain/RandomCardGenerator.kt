package blackjack.domain

val randomCardGenerator = CardGenerator {
    Card(Rank.random(), Suit.random())
}
