package sistemasOperacionais;

public class PrioridadeCooperativa implements Runnable{
	private final String Nome;
	private final int Prioridade;
	private final int TempoExec;
	
	public PrioridadeCooperativa(String nome, int prioridade, int tempoExec) {
		this.TempoExec = tempoExec;
		this.Prioridade = prioridade;
		this.Nome = nome;
	}

	public int getPrioridade() {
		return Prioridade;
	}
	
	@Override
	public void run() {
		System.out.println("Iniciando "+Nome+" com prioridade "+Prioridade+" e tempo de execução de "+TempoExec+" segundos");
		for(int i=1;i<=TempoExec;i++) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Executando "+Nome+" tempo "+i);
		}
		System.out.println(Nome+" concluido");
	}
}
