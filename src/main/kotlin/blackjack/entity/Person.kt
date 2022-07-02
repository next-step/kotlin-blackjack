package blackjack.entity

interface Person {
    val wallet: Wallet
    val limit: Int
    fun draw(): Wallet
}
