package blackjack.domain.user

data class UserNames(val nameSet: Set<String>) : Iterable<String> by nameSet
