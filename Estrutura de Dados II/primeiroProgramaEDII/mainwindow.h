#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "gerador.h"
#include <QMainWindow>

using namespace MAR;

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_btnGerarSemente_clicked();

    void on_btnGerarNumeros_clicked();

    void verificarCampos();

private:
    Ui::MainWindow *ui;
    Gerador *aleatorio;
};
#endif // MAINWINDOW_H
