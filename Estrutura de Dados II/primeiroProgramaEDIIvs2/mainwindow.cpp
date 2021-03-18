#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>
MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    this->setWindowFlags(this->windowFlags() | Qt::MSWindowsFixedSizeDialogHint);
    ui->setupUi(this);
    aleatorio = nullptr;
    this->setWindowState(Qt::WindowMaximized);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::verificarCampos() {
    if(ui->lineQuantidade->text().isEmpty())
        throw QString("Digite uma quantidade de numeros!!");
    if(ui->lineSemente->text().isEmpty())
        throw QString("Digite uma semente!!");
}

void MainWindow::on_btnGerarSemente_clicked()
{
    ui->lineSemente->setText(QString::number(time(NULL)));
}

void MainWindow::on_btnGerarNumeros_clicked()
{
    try {
        if(this->aleatorio != nullptr)
            delete  this->aleatorio;
        this->verificarCampos();
        this->aleatorio = new Gerador(ui->lineQuantidade->text().toInt(), ui->lineSemente->text().toInt());
        this->aleatorio->gerarNumeros();
        ui->textOriginal->setText(this->aleatorio->obterVetorOriginal());
        ui->btnBuscarOriginal->setEnabled(true);
        ui->btnSelectionSortC->setEnabled(true);
        ui->btnSelectionSortD->setEnabled(true);
        ui->lineBuscarOriginal->setEnabled(true);
    }  catch (QString &msg) {
        QMessageBox::critical(this,"ERRO",msg);
    }
}

void MainWindow::on_btnBuscarOriginal_clicked()
{
    int pos = aleatorio->buscaSequencial(ui->lineBuscarOriginal->text().toInt());
    if(pos < 0) {
        ui->lblBuscarOriginal->setText("O numero não esta registrado!!");
    } else {
        ui->lblBuscarOriginal->setText("O numero esta na posição: " + QString::number(pos));
    }
}

void MainWindow::on_btnSelectionSortC_clicked()
{
    aleatorio->ordenarSelectionSortC();
    ui->textSelectionSort->setText(aleatorio->obterVetorSelectionSort());
    ui->btnBuscaSSI->setEnabled(true);
    ui->btnBuscaSSR->setEnabled(true);
    ui->lineBuscaSSI->setEnabled(true);
    ui->lineBuscaSSR->setEnabled(true);
}

void MainWindow::on_btnSelectionSortD_clicked()
{
    aleatorio->ordenarSelectionSortD();
    ui->textSelectionSort->setText(aleatorio->obterVetorSelectionSort());
    ui->btnBuscaSSI->setEnabled(true);
    ui->btnBuscaSSR->setEnabled(true);
    ui->lineBuscaSSI->setEnabled(true);
    ui->lineBuscaSSR->setEnabled(true);
}

void MainWindow::on_btnBuscaSSR_clicked()
{
    int pos = aleatorio->buscaBinariaRecursiva(ui->lineBuscaSSR->text().toInt());
    if(pos < 0) {
        ui->lblBuscaSSR->setText("O numero não esta registrado!!");
    } else {
        ui->lblBuscaSSR->setText("O numero esta na posição: " + QString::number(pos));
    }

}

void MainWindow::on_btnBuscaSSI_clicked()
{
    int pos = aleatorio->buscaBinariaInterativa(ui->lineBuscaSSI->text().toInt());
    if(pos < 0) {
        ui->lblBuscaSSI->setText("O numero não esta registrado!!");
    } else {
        ui->lblBuscaSSI->setText("O numero esta na posição: " + QString::number(pos));
    }
}
