#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    aleatorio = nullptr;
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_btnGerarSemente_clicked()
{
    ui->lineSemente->setText(QString::number(time(NULL)));
}

void MainWindow::on_btnGerarNumeros_clicked()
{
    try {
        if(aleatorio != nullptr)
            delete aleatorio;
        this->verificarCampos();
        this->aleatorio = new Gerador(ui->lineQuantidade->text().toInt(),ui->lineSemente->text().toInt());
        this->aleatorio->gerarNumeros();
        ui->textVetorNumeros->setText(this->aleatorio->obterVetor());
    }  catch (QString &msg) {
        QMessageBox::critical(this,"ERRO",msg);
    } catch (std::bad_alloc &ba) {
        QMessageBox::critical(this,"ERRO","falta de memoria");
    }
}

void MainWindow::verificarCampos() {
    if(ui->lineQuantidade->text().isEmpty())
        throw QString("Digite uma quantidade de numeros!!");
    if(ui->lineSemente->text().isEmpty())
        throw QString("Digite uma semente!!");
}

void MainWindow::on_btnBuscar_clicked()
{
    int pos = aleatorio->buscarNumero(ui->lineBuscar->text().toInt());
    if(pos < 0) {
        ui->labelBusca->setText("O numero não esta registrado nesse vetor!");
    } else {
        ui->labelBusca->setText("O numero ta na posição: "+QString::number(pos));
    }
}
