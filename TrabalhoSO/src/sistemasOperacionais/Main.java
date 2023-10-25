package sistemasOperacionais;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		int Escolha;
		int quant_processos;
		Scanner in = new Scanner(System.in);

		System.out.println("Escolha o algoritmo de escalonamento que deseja utilizar: \n" + "1 - RoundRobin \n"
				+ "2 - Shortest Job First \n" + "3 - Prioridade Cooperativa \n" + "4 - Prioridade preemptivo \n");
		Escolha = in.nextInt();// Usuário insere manualmente qual algoritmo deseja usar

		switch (Escolha) { // seleciona o algoritmo com base na escolha do usuário;

		case 1: // execução do RoundRobin;

			Object lock = new Object();
			List<RoundRobin> listaProcessos = new ArrayList<>();
			int quantum;
			String nome;
			int tempoExecucao;

			System.out.println("Digite a quantidade de processos que gostaria de criar: ");
			quant_processos = in.nextInt();

			System.out.println("Informe o quantum: ");
			quantum = in.nextInt();

			// entrada dos valores e criação das Threads
			for (int i = 0; i < quant_processos; i++) {
				System.out.println("Digite o nome do processo: ");
				nome = in.next();

				System.out.println("Digite o tempo de execução: ");
				tempoExecucao = in.nextInt();

				RoundRobin thread = new RoundRobin(nome, tempoExecucao, quantum, lock);
				listaProcessos.add(thread);
			}

			for (RoundRobin t : listaProcessos) {
				Thread thread = new Thread(t);
				thread.start();
			}

			break;

		case 2: // Execução do SJF
			List<ShortestJobFirst> listaSJF = new ArrayList<>();

			System.out.println("Digite a quantidade de processos que gostaria de criar: ");
			quant_processos = in.nextInt();

			// entrada dos valores e criação das Threads
			for (int i = 0; i < quant_processos; i++) {
				System.out.println("Digite o nome do processo: ");
				nome = in.next();

				System.out.println("Digite o tempo de execução: ");
				tempoExecucao = in.nextInt();

				ShortestJobFirst thread = new ShortestJobFirst(tempoExecucao, nome);
				listaSJF.add(thread);
			}
			// compara os itens da lista e ordena com base no tempo de execução (do menor
			// para o maior)
			listaSJF.sort((t1, t2) -> Integer.compare(t1.getTempoExec(), t2.getTempoExec()));

			for (ShortestJobFirst processo : listaSJF) {
				Thread thread = new Thread(processo);
				thread.start();
				try {
					Thread.sleep(1000);
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case 3:
			List<PrioridadeCooperativa> listaPorPrioridade = new ArrayList<>();

			System.out.println("Digite a quantidade de processos que gostaria de criar: ");
			quant_processos = in.nextInt();

			for (int i = 0; i < quant_processos; i++) {
				System.out.println("Digite o nome do processo: ");
				nome = in.next();

				System.out.println("Digite o tempo de execução: ");
				tempoExecucao = in.nextInt();

				System.out.println("Defina a prioridade para o processo de 1 a 5");
				int Prioridade = in.nextInt();

				listaPorPrioridade.add(new PrioridadeCooperativa(nome, Prioridade, tempoExecucao));
			}

			listaPorPrioridade.sort((t1, t2) -> Integer.compare(t1.getPrioridade(), t2.getPrioridade()));

			for (PrioridadeCooperativa thread : listaPorPrioridade) {
				Thread t1 = new Thread(thread);
				t1.start();
				try {
					Thread.sleep(1000);
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;

		case 4:

			Object Lock = new Object();
			PriorityQueue<PrioridadePreemptivo> filaPrioridade = new PriorityQueue<PrioridadePreemptivo>((p1, p2) -> Integer.compare(p1.getPrioridade(), p2.getPrioridade()));

			System.out.println("Digite a quantidade de processos que gostaria de criar: ");
			quant_processos = in.nextInt();

			for (int i = 0; i < quant_processos; i++) {
				System.out.println("Digite o nome do processo: ");
				nome = in.next();

				System.out.println("Digite o tempo de execução: ");
				tempoExecucao = in.nextInt();

				System.out.println("Defina a prioridade para o processo de 1 a 5");
				int Prioridade = in.nextInt();

				PrioridadePreemptivo processo = new PrioridadePreemptivo(nome, Prioridade, tempoExecucao, Lock);
				filaPrioridade.add(processo);
				new Thread(processo).start();
				synchronized(Lock) {
					try {
					Lock.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
/*
 * made by: Razi^-^ /ᐠ - ˕ -マ
 */