package br.com.luiz.service.exception;

public class ErroAutenticacaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ErroAutenticacaoException(String mensagem) {
		super(mensagem);
	}

	

}
