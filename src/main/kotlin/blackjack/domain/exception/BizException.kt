package blackjack.domain.exception

class BizException(
    errorStatus: ErrorStatus
) : RuntimeException(errorStatus.getErrorMessage())
