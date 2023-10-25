
using System.Runtime.CompilerServices;

public abstract class  Task
{
    private string taskID {

        get
        {
            return taskID;
        }
        set
        {
            taskID = value;
        }
    }

    private string descriere
    {
        get
        {
            return descriere;
        }
        set
        {
            descriere = value;
        }
    }

    public Task() { }

    public abstract void execute();

    public override string ToString()
    {
        return base.ToString();
    }

    public override int GetHashCode()
    {
        return base.GetHashCode();
    }

    public override bool Equals(object? obj)
    {
        return base.Equals(obj);
    }
}

public class MessageTask : Task
{
    private string mesaj { get; set; }
    private string from { get; set; }

    public override void execute()
    {
        throw new NotImplementedException();
    }


}