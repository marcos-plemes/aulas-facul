#include "gerador.h"
using namespace MAR;
Gerador::Gerador()
{
    this->quantidade = 0;
    this->semente = 0;
    this->vetor = nullptr;
}

Gerador::Gerador(int quantidade, int semente) {
    this->setQuantidade(quantidade);
    this->setSemente(semente);
    this->criarNovoVetor();
}

Gerador::~Gerador(){
    delete[] vetor;
}

void Gerador::gerarNumeros() {
    srand(this->semente);
    for(int i = 0; i < this->quantidade; i++) {
        vetor[i] = (rand() % 20000) + (-10000);
    }
}

void Gerador::criarNovoVetor() {
    if(this->vetor != nullptr)
        delete[] this->vetor;
    try {
         this->vetor = new int[this->quantidade];
    }  catch( std::bad_alloc &ba) {
        throw ba.what();
    }
}

QString Gerador::obterVetor() {
    QString vetorString = "";
    for(int i = 0; i < this->quantidade; i++) {
        vetorString += QString::number(this->vetor[i]);
        vetorString += " | ";
    }
    return vetorString;
}
