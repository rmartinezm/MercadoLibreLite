package cs.roberto.mercadolibrelite.core.exceptionktx

/* Message of the exception or the class name if message is null */
val Exception.messageOrClassName: String
    get() = message ?: javaClass.simpleName