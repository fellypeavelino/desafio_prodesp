export interface Tarefa {
    id: number;
    titulo: string;
    descricao?: string;
    completada: boolean;
    data: string;
    usuario_id: number;
    categoria_id?: number;
}