#ifndef GERADOR_H
#define GERADOR_H
#include <QString>
class Gerador
{
private:
    int quantidade;
    int semente;
    int *vetor;
    int *vetorSelectionSort;
    bool selecSortiSCres;

    int buscaBinariaRecursiva(int inicio, int final, int numero);
public:
    Gerador();

    Gerador(int quantidade, int semente);

    ~Gerador();

    void criarNovoVetor();

    void gerarNumeros();

    QString obterVetorOriginal();

    int buscaSequencial(int numero);

    void trocaNumeros(int &a, int &b);

    void ordenarSelectionSortC();

    void ordenarSelectionSortD();

    int buscaBinariaRecursiva(int numero);

    int buscaBinariaInterativa(int numero);

    QString obterVetorSelectionSort();
};

#endif // GERADOR_H

