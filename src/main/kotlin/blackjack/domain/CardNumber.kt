package blackjack.domain

enum class CardNumber(val symbol: String, val getScore: (Boolean) -> Int) {

    Ace("A", { if (it) 11 else 1 }),
    Two("2", { 2 }),
    Three("3", { 3 }),
    Four("4", { 4 }),
    Five("5", { 5 }),
    Six("6", { 6 }),
    Seven("7", { 7 }),
    Eight("8", { 8 }),
    Nine("9", { 9 }),
    TEN("10", { 10 }),
    King("K", { 10 }),
    Queen("Q", { 10 }),
    Jack("J", { 10 })
    ;
}
