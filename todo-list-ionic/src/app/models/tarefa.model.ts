export interface Tarefa {
    id: number;
    titulo: string;
    descricao?: string;
    completada: boolean;
    data: string;
    usuarioId: number;
    categoriaId?: number;
}