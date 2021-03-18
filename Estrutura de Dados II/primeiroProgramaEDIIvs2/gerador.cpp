#include "gerador.h"
Gerador::Gerador()
{
    this->quantidade = 0;
    this->semente = 0;
    this->vetor = nullptr;
    this->vetorSelectionSort = nullptr;
    this->selecSortiSCres = false;
}

Gerador::Gerador(int quantidade, int semente) {
    this->quantidade = quantidade;
    this->semente = semente;
    this->vetor = nullptr;
    this->vetorSelectionSort = nullptr;
    this->selecSortiSCres = false;
    this->criarNovoVetor();
}

Gerador::~Gerador() {
    if(vetor != nullptr)
        delete [] vetor;
    if(vetorSelectionSort != nullptr)
        delete [] vetorSelectionSort;
}

void Gerador::criarNovoVetor() {
    try {
        if(this->vetor != nullptr)
            delete [] vetor;
        if(this->vetorSelectionSort != nullptr)
            delete [] vetorSelectionSort;
        this->vetor = new int[this->quantidade];
        this->vetorSelectionSort = new int[this->quantidade];
    }  catch (std::bad_alloc &ba) {
        throw ba.what();
    }
}

void Gerador::gerarNumeros() {
    srand(this->semente);
    for(int i = 0; i < this->quantidade; i++) {
        vetor[i] = (rand() % 20000) + (-10000);
        vetorSelectionSort[i] = vetor[i];
    }
}

QString Gerador::obterVetorOriginal() {
    QString vetorString = "";
    for(int i = 0; i < this->quantidade; i ++) {
        vetorString += QString::number(vetor[i]) + " | ";
    }
    return vetorString;
}

int Gerador::buscaSequencial(int numero) {
    for(int i = 0; i < this->quantidade; i++) {
        if(numero == vetor[i])
            return i;
    }
    return -1;
}

void Gerador::trocaNumeros(int &a, int &b) {
    int aux;
    aux = a;
    a = b;
    b = aux;
}

void Gerador::ordenarSelectionSortC() {
    for(int i = 0; i < quantidade; i++) {
        for(int j = i+1; j < quantidade; j++) {
            if(vetorSelectionSort[i] > vetorSelectionSort[j])
                this->trocaNumeros(vetorSelectionSort[i],vetorSelectionSort[j]);
        }
    }
    selecSortiSCres = true;
}

void Gerador::ordenarSelectionSortD() {
    for(int i = 0; i < quantidade; i++) {
        for(int j = i+1; j < quantidade; j++) {
            if(vetorSelectionSort[i] < vetorSelectionSort[j])
                this->trocaNumeros(vetorSelectionSort[i], vetorSelectionSort[j]);
        }
    }
    selecSortiSCres = false;
}

int Gerador::buscaBinariaRecursiva(int numero) {
    return this->buscaBinariaRecursiva(0, quantidade, numero);
}

int Gerador::buscaBinariaRecursiva(int inicio, int final, int numero) {
    int i = (inicio + final) /2;
    if(inicio > final)
        return -1;
    if(vetorSelectionSort[i] == numero)
        return i;
    if((vetorSelectionSort[i] < numero && selecSortiSCres) || (vetorSelectionSort[i] > numero && !selecSortiSCres))
        return this->buscaBinariaRecursiva(i+1, final, numero);
     else
        return this->buscaBinariaRecursiva(inicio, i-1, numero);

}

int Gerador::buscaBinariaInterativa(int numero) {
    int inicio = 0;
    int final = quantidade;
    int i;
    while (inicio <= final) {
        i = (inicio + final) /2;
        if(vetorSelectionSort[i] == numero)
            return i;
        if((vetorSelectionSort[i] < numero && selecSortiSCres) || (vetorSelectionSort[i] > numero && !selecSortiSCres))
            inicio = i + 1;
        else
            final = i - 1;
    }
    return -1;
}

QString Gerador::obterVetorSelectionSort() {
    QString vetorString = "";
    for(int i = 0; i < quantidade; i++) {
        vetorString += QString::number(vetorSelectionSort[i]) + " | ";
    }
    return vetorString;
}
