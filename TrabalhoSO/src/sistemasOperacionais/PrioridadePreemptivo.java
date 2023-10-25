package sistemasOperacionais;

public class PrioridadePreemptivo implements Runnable {
	private final String nome;
	private final int prioridade;
	private final int tempoExecucao;
	private final Object lock;

	public PrioridadePreemptivo(String nome, int prioridade, int tempoExecucao, Object lock) {
		this.nome = nome;
		this.prioridade = prioridade;
		this.tempoExecucao = tempoExecucao;
		this.lock = lock;
	}

	public String getNome() {
		return nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public int getTempoExecucao() {
		return tempoExecucao;
	}

	@Override
	public void run() {
		synchronized (lock) {
			System.out.println("Iniciando " + nome + " com prioridade " + prioridade);
			Thread.currentThread().setPriority(prioridade);
			if(Thread.currentThread().getPriority() == prioridade) {
			try {
				for (int i = 0; i < tempoExecucao; i++) {
					System.out.println("Executando " + nome + " no tempo " + i);
					Thread.sleep(1000);
					lock.notify();
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			System.out.println(nome+ " execução completa.");
			lock.notify();
		}
	}
}