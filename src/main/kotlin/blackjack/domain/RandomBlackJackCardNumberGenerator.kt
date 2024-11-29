package blackjack.domain

class RandomBlackJackCardNumberGenerator : BlackJackCardNumberGenerator {
    override fun getNumber(): BlackJackCardNumber {
        return BlackJackCardNumber.entries.shuffled().first()
    }
}
