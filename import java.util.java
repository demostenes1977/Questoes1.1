import java.util.PriorityQueue;

class Pedido {
    int id;
    String cliente;
    boolean vip;

    public Pedido(int id, String cliente, boolean vip) {
        this.id = id;
        this.cliente = cliente;
        this.vip = vip;
    }
}

class SistemaPedidos {
    private PriorityQueue<Pedido> filaPedidos;

    public SistemaPedidos() {
        filaPedidos = new PriorityQueue<>((p1, p2) -> {
            if (p1.vip && !p2.vip) {
                return -1; // VIP tem prioridade
            } else if (!p1.vip && p2.vip) {
                return 1;
            } else {
                return p1.id - p2.id; // Ordena por ID se não for VIP
            }
        });
    }

    public void adicionarPedido(int id, String cliente, boolean vip) {
        filaPedidos.offer(new Pedido(id, cliente, vip));
    }

    public Pedido processarProximoPedido() {
        return filaPedidos.poll();
    }

    public void visualizarPedidos() {
        for (Pedido pedido : filaPedidos) {
            System.out.println("ID: " + pedido.id + ", Cliente: " + pedido.cliente + ", VIP: " + pedido.vip);
        }
    }

    public void removerPedido(int id) {
        filaPedidos.removeIf(pedido -> pedido.id == id);
    }

    public void atualizarPrioridade(int id, boolean vip) {
        for (Pedido pedido : filaPedidos) {
            if (pedido.id == id) {
                pedido.vip = vip;
                filaPedidos.remove(pedido); // Remove e reinserir para atualizar a prioridade
                filaPedidos.offer(pedido);
                break;
            }
        }
    }
}SS