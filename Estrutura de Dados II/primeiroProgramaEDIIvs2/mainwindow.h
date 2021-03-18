#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "gerador.h"
#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    void verificarCampos();

private slots:
    void on_btnGerarSemente_clicked();

    void on_btnGerarNumeros_clicked();

    void on_btnBuscarOriginal_clicked();

    void on_btnSelectionSortC_clicked();

    void on_btnSelectionSortD_clicked();

    void on_btnBuscaSSR_clicked();

    void on_btnBuscaSSI_clicked();

private:
    Ui::MainWindow *ui;
    Gerador *aleatorio;
};
#endif // MAINWINDOW_H
