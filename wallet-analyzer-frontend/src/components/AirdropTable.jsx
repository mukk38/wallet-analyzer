const AirdropTable = ({ data }) => {
  if (!data || data.length === 0) {
    return <div className="text-gray-500">Airdrop verisi bulunamadı.</div>;
  }

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white shadow-md rounded-lg">
        <thead>
          <tr className="bg-blue-600 text-white">
            <th className="py-3 px-4 text-left">Token</th>
            <th className="py-3 px-4 text-left">Sembol</th>
            <th className="py-3 px-4 text-left">Miktar</th>
            <th className="py-3 px-4 text-left">Tarih</th>
          </tr>
        </thead>
        <tbody>
          {data.map((airdrop, index) => (
            <tr
              key={index}
              className={`border-t ${index % 2 === 0 ? "bg-gray-50" : "bg-white"}`}
            >
              <td className="py-2 px-4">{airdrop.tokenName}</td>
              <td className="py-2 px-4">{airdrop.tokenSymbol}</td>
              <td className="py-2 px-4">{parseFloat(airdrop.value).toFixed(4)}</td>
              <td className="py-2 px-4">
                {new Date(airdrop.receivedAt).toLocaleDateString()}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AirdropTable;
