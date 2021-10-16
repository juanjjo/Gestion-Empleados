package ar.edu.unju.fi.POO2020C01Equipo01.exceptions;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 0;
	private Throwable cause;
	

    /**
     * 
     * Constructor con un mensaje explicativo
     * @param message
     *            Detalle sobre la razon de la excepcion
     */
	public ServiceException(String message) {
        super(message);
    }
	/**
    * Construye una nueva excepcion con la causa especificada.
    * @param cause La causa.
    */
	public ServiceException(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }
	
	/**
     * Returns the cause of this exception or null if the cause is nonexistent
     * or unknown.
     * Retorna la causa de esta exceppcon o nulo si la causa no existe
     * @return la causa de la excepcion o nullo si la causa no existe o es desconocida
     */
    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
