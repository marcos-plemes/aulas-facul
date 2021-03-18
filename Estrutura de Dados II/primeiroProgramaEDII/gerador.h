#ifndef GERADOR_H
#define GERADOR_H
#include <QString>
namespace MAR {
class Gerador
{
private:
    int quantidade;
    int semente;
    int *vetor;

public:
    Gerador();

    Gerador(int quantidade, int semente);

    ~Gerador();

    void gerarNumeros();

    void criarNovoVetor();

    QString obterVetor();

    int buscarNumero(int numero);

    int getQuantidade() {
        return this->quantidade;
    }

    void setQuantidade(int quantidade) {
        if(quantidade <= 0)
            throw QString("Numero tem que ser maior que 0!!");
        this->quantidade = quantidade;
    }

    int getSemente() {
        return this->semente;
    }

    void setSemente(int semente) {
        this->semente = semente;
    }

};

#endif // GERADOR_H
}
