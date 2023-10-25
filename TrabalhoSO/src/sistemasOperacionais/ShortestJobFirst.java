package sistemasOperacionais;

public class ShortestJobFirst implements Runnable {
	private final int TempoExec;
	private final String Nome;
	
	public ShortestJobFirst(int tempoExec, String nome) {
		this.TempoExec = tempoExec;
		this.Nome = nome;
	}

	public int getTempoExec() {
		return TempoExec;
	}

	@Override
	public void run() {
		System.out.println("Iniciando " + Nome + " com tempo de execução: " + TempoExec + " segundos");
		for (int i = TempoExec; i > 0; i--) {
			System.out.printf("Executando processo %s segundo %d \n", Nome, i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Processo " + Nome + " concluído");
	}
}
